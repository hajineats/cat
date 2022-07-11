import axios from "axios";

const INSUFFICIENT_ARGUMENT = "Insufficient arguments provided"
const INCORRECT_TYPES = "Parameters types are invalid"

const baseUrl = "http://localhost:8080/"
export const getData = async (url)=> {
	const res = await axios.get(`${baseUrl}${url}`).catch(handleError)
	console.log("fetched data from", `${baseUrl}${url}`, res.data)
	return res.data
}

export const postData = async (url, data)=> {
	const res = await axios.post(`${baseUrl}${url}`, data).catch(handleError)
	return res.data
}

export const assertNumber = (param) =>{
	if (!param) throw new Error(INSUFFICIENT_ARGUMENT)
	if (typeof param !== "number") throw new Error(INCORRECT_TYPES)
}

export const assertObject = (param) =>{
	if (!param) throw new Error(INSUFFICIENT_ARGUMENT)
	if (typeof param !== "object") throw new Error(INCORRECT_TYPES)
}

export const handleError = async (error) => {
	throw error
	// if (!error?.response) {
	// 	// throw new Error(
	// 	// 	'The server seems to be down :(  Try again later.',
	// 	// );
	// }
	// throw new Error(`${error.response.data.message}`)
}