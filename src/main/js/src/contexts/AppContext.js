import React, {useEffect, useState} from "react";
import {
	getFLQuestionSet,
	getMSTModuleByModuleNumber,
	getUserDocument,
	submitFLResult,
	submitMSTResult
} from "../services";
import {useNavigate} from "react-router-dom";

const AppContext = React.createContext([])

const AppContextProvider = ({children}) => {
	const [questionList, setQuestionList] = useState([]);
	const [userDocument, setUserDocument] = useState({});
	const [userResponses, setUserResponses] = useState({});
	const [currentQuestion, setCurrentQuestion] = useState(null);
	const [currentResponse, setCurrentResponse] = useState(null);
	const navigate = useNavigate()

	useEffect(() => {
		if (currentQuestion) {
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

		showInstructionAfterLogin(user)
	}

	const handleTestSubmission = async () => {
		// Map unanswered questions to an incorrect answer
		let responses = {}
		questionList.forEach(q => {
			responses[q.id] = userResponses.hasOwnProperty(q.id) ?  userResponses[q.id] : 'placeholder'
		})

		// submit responses
		if (userDocument.shouldTakes[0] === "FL") {
			await submitFLResult(userDocument.id, responses)
		} else {
			await submitMSTResult(userDocument.id, responses)
		}

		// set user document
		const updatedUserDoc = await getUserDocument(userDocument.id)
		setUserDocument(updatedUserDoc)

		// if the user has remaining shouldTakes, it's either the student needs to do second part of MST or has to come back on day 2
		showInstructionAfterTestSubmission(updatedUserDoc)
	}

	const beginTest = async () => {
		const questions = userDocument.shouldTakes[0] === "FL" ?
			await getFLQuestionSet() :
			await getMSTModuleByModuleNumber(userDocument.currentModule);

		setQuestionList(questions)
		setCurrentQuestion(questions[0])
		showTestScreen()
	}

	/**
	 * You call this function when you are on the TestScreen and you want to go onto the instruction screen.
	 * Note: this function should only be called after you updated User Doc.
	 *
	 * user.shouldTakes is popped only after the backend processes a submission of one examination results
	 *
	 * In this function, we check if the student:
	 * 	has submitted all the exams it needs to for both Day 1 and 2
	 * 	has just done first module of MST, and needs to do second module
	 * 	has submitted one exam, and the remaining exam is MST (that means
	 *  has done first module of MST (need screen to proceed to the next module)
	 *  second module (exam has ended)
	 *  fixed length module (exam has ended)
	 *
	 * @param userDoc User model doc
	 */
	const showInstructionAfterTestSubmission = (userDoc) => {
		// has submitted all the exams it needs to for both Day 1 and 2
		if (userDoc.shouldTakes.length === 0) {
			endTest("You have completed all your exams for Day 1 and 2. Do not close the window, and let the supervisor know.")
			return
		}

		// has just done first module of MST, and needs to do second module
		if (userDoc.shouldTakes[0] === "MST" && userDoc.currentModule !== 0) {
			// note moduleNumber = 0 is a default module that every participant takes on their first module
			showInstruction("next up is the second part of your examination. It will be your last one.")
			return
		}

		// has just done FL, and needs to come back on the next day to start MST
		if (userDoc.shouldTakes[0] === "MST" && userDoc.currentModule === 0) {
			// this is reached when you have done FL test, but have one more exam to go
			endTest("You have completed all your exams for Day 1. Do not close the window, and let the supervisor know.")
			return
		}

		// has just done MST, and needs to come back on the next day to start FL
		if (userDoc.shouldTakes[0] === "FL"){
			// this is reached when you have done MST test, but have one more exam (FL) to go
			endTest("You have completed all your exams for Day 1. Do not close the window, and let the supervisor know.")
			return
		}

		throw new Error(userDoc)
	}

	/**
	 * Instruction screen is a screen that shows a generic info/instruction. You call this function
	 * when you are on the LoginScreen and you want to go onto the instruction screen.
	 *
	 * @param userDoc User model object
	 */
	const showInstructionAfterLogin = (userDoc) => {
		// if the user has no more exams to take
		if (userDoc.shouldTakes.length === 0) {
			endTest("You have completed all your exams for Day 1 and 2. Do not close the window, and let the supervisor know.")
			return
		}

		// if the user has more
		if (userDoc.shouldTakes[0] === "FL") {
			showInstruction(`You are taking FL!`)
		} else {
			if (userDoc.currentModule === 0) {
				showInstruction("You are taking first module of MST")
			} else {
				showInstruction("You are taking second module of MST. Why was this called when you came from the login screen? Did you accidentally close the window after submitting the first booklet and come back?")
			}
		}
	}

	const showTestScreen = ()=>{
		navigate("/test")
	}


	const endTest = (message) => {
		navigate('/end', {state: {msg: message}})

	}
	const showInstruction = (message)=>{
		navigate('/instruction', {state: {msg: message}});
	}


	const value = {
		currentQuestion,
		questionList,
		currentResponse,
		setQuestionList,
		userResponses,
		handleTestSubmission,
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
