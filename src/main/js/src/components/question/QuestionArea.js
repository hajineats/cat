import styled from "styled-components";
import {useContext} from "react";
import {AppContext} from "../../contexts/AppContext";

const Container = styled.div`
`
const Header = styled.h4`
  //background-color: grey;
  //height: 70px;
  //width: 200px;
  //margin-bottom: 10px;
`
const Content = styled.div`
  //background-color: grey;
  width: 100%;
`
const Image = styled.img`
	max-width: 100%;
`
export const QuestionArea = () => {
	const {currentQuestion} = useContext(AppContext)
	console.log(currentQuestion)
	return (
		<Container>
			<Header>
				{currentQuestion.id}
			</Header>
			<Content>
				{currentQuestion.imageContent.length != 0 && <Image src={`\/images\/${currentQuestion.imageContent}`}/>}
				<br/>
				{currentQuestion.textContent}
			</Content>
		</Container>
	)
}