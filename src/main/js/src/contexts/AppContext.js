import React, {useEffect, useState} from "react";
import {getFLQuestionSet, getMSTModuleByModuleNumber, getUserDocument, submitFLResult} from "../services";
import {useNavigate} from "react-router-dom";


const AppContext = React.createContext([])

const AppContextProvider = ({children}) => {
    const [questionList, setQuestionList] = useState([]);
    const [userDocument, setUserDocument] = useState({});
    const [userResponses, setUserResponses] = useState({});
    const [currentQuestion, setCurrentQuestion] = useState();
    const [currentResponse, setCurrentResponse] = useState(null);
    const [instructions, setInstructions] = useState('');

    const navigate = useNavigate()

    useEffect(() => {
        if(currentQuestion){
            setCurrentResponse(userResponses[currentQuestion.id])
        }
    }, [userResponses, currentQuestion])

    const addUserResponse = (optionId) => {
        userResponses[currentQuestion.id] = optionId
        setUserResponses({...userResponses})
    }

    const changeCurrentQuestion = (questionId) => {
        const question = questionList.filter(e => e.id === questionId)[0]
        setCurrentQuestion(question)
    }


    const handleLogin = async (userId) => {
        const user = await getUserDocument(userId)
        setUserDocument(user)

        if (user.shouldTakes.length === 0) {
            // TODO: account for case where shouldTakes is empty
            // Probably a screen that informs the user
            return;
        }

		const questions = user.shouldTakes[0] === "FL" ?
			await getFLQuestionSet() :
			await getMSTModuleByModuleNumber(user.currentModule);
		setQuestionList(questions)
        setCurrentQuestion(questions[0])
        setInstructions("adfhlkjhlkjhlks")
        navigate("/instructions")
	}

    const beginTest = () => {
        console.log(currentQuestion)
        navigate("/test")
    }

    const value = {
        currentQuestion,
        questionList,
        currentResponse,
        setQuestionList,
        userResponses,
        instructions,
        changeCurrentQuestion,
        handleLogin,
        beginTest,
        addUserResponse
    }

    return (
        <AppContext.Provider value={value}>
            {children}
        </AppContext.Provider>
    )
}


export {
    AppContext,
    AppContextProvider
}
