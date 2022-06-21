import styled from "styled-components";


const ResponseArea = ()=>{

	const Container = styled.div`
	  	display: grid;
	  	justify-content: center;
	  	grid-template-columns: 1fr 1fr;
	  gap: 10px;
		//background-color: blueviolet;
	`
	const Response = styled.div`
		background-color: darkgray;
	  	height: 100px;
	`

	return (
		<Container>
			<Response />
			<Response />
			<Response />
			<Response />
		</Container>
	)
}

const QuestionArea = ()=>{
	const Container = styled.div`
	`
	const Header = styled.div`
	  	background-color: grey;
		height: 70px;
	  	width: 200px;
	  margin-bottom: 10px;
	`
	const Content = styled.div`
		background-color: grey;
	  	width: 100%;
	  height: 200px;
	`
	return (
		<Container>
			<Header />
			<Content />
		</Container>
	)
}


const Question = ()=>{
	const Container = styled.div`
	  	padding: 20px;
	  	display: grid;
	  	gap: 10px;
	  	justify-content: stretch;
	  	align-content: start;
	  	overflow-y: scroll;
	`
	return(
		<Container>
			<QuestionArea />
			<ResponseArea />
		</Container>
	)
}

export default Question

