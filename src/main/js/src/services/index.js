import {assertNumber, assertObject, postData, getData} from './util'

export const getUserDocument = async (userId) => {
	return getData(`users/${userId}`)
}

export const getFLQuestionSet = async () => {
	return getData('fixed')
}

export const getMSTModuleByModuleNumber = async (moduleNumber) => {
	return getData(`mst/${moduleNumber}`);
}

export const submitFLResult = async (userId, responses) => {
	const body = {
		userId: userId,
		responses: responses
	}
	console.log("post /fixed with body", body)
	return postData("fixed", body)
}

export const submitMSTResult = async (userId, responses) => {
	const body = {
		userId: userId,
		responses: responses
	}
	console.log("post /mst with body", body)
	return postData("mst", body)
}



