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
	assertNumber(userId)
	assertObject(responses)
	return postData("fixed", {userId,responses})
}

export const submitMSTResult = async (userId, responses) => {
	assertNumber(userId)
	assertObject(responses)
	return postData("mst", {userId,responses})
}



