import styled, {css} from "styled-components";
import {useContext} from "react";
import {AppContext} from "../../contexts/AppContext";

const Container = styled.div`
  background-color: grey;
  height: 50px;
  margin-bottom: 10px;
  ${props => (props.selected || props.answered) && css`
    background-color: darkseagreen;
  `}
`
export const SidebarItem = ({question}) => {
	const {currentQuestion, changeCurrentQuestion, userResponses} = useContext(AppContext)

	return (
		<Container
			selected={question.id === currentQuestion.id}
			answered={userResponses[question.id] !== undefined
				&& userResponses[question.id] !== null
				&& userResponses[question.id] !== ''}
			onClick={() => changeCurrentQuestion(question.id)}>
			{question.id}
		</Container>
	)
}