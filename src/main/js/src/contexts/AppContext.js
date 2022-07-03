import React, {useEffect, useState} from "react";
import {getFLQuestionSet, getMSTModuleByModuleNumber, getUserDocument, submitFLResult} from "../services";
import {useNavigate} from "react-router-dom";
import fakeQuestions from '../utils/fakeData.json'



const AppContext = React.createContext([])

const AppContextProvider = ({children})=>{
	const [currentQuestions, setCurrentQuestions] = useState(fakeQuestions);
	const [userDocument, setUserDocument] = useState({});
	const [userResponses, setUserResponses] = useState({"M00001": "op1"});
	const [currentQuestion, setCurrentQuestion] = useState(fakeQuestions[0]);
	const [currentResponse, setCurrentResponse] = useState(null);

	const navigate = useNavigate()

	useEffect(()=>{
		setCurrentResponse(userResponses[currentQuestion.id])
	}, [userResponses, currentQuestion])

	const addUserResponse = (optionId)=>{
		userResponses[currentQuestion.id] = optionId
		setUserResponses({...userResponses})
	}

	const changeCurrentQuestion = (questionId)=>{
		const question = currentQuestions.filter(e=>e.id===questionId)[0]
		setCurrentQuestion(question)
	}


	const handleLogin = async (userId)=>{
		// const user = await getUserDocument(userId)
		const user = {
			userId: Number(userId),
			shouldTakes: ["MST", "FL"],
			currentModule: 0,
		}
		setUserDocument(user)

		if (user.shouldTakes.length === 0) {
			// TODO: account for case where shouldTakes is empty
			// Probably a screen that informs the user
			return;
		}

		// const questions = user.shouldTakes[0] === "FL" ?
		// 	await getFLQuestionSet() :
		// 	await getMSTModuleByModuleNumber(user.currentModule);
		// setCurrentQuestions(fakeQuestions)
		navigate("/test")
	}

	const value = {
		currentQuestion,
		currentQuestions,
		currentResponse,
		setCurrentQuestions,
		userResponses,
		changeCurrentQuestion,
		handleLogin,
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
