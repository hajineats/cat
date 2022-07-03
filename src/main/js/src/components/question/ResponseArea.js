import styled from "styled-components";
import {Response} from "./Response";
import {useContext} from "react";
import {AppContext} from "../../contexts/AppContext";

const Container = styled.div`
  display: grid;
  justify-content: center;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
`

export const ResponseArea = () => {
	const {currentQuestion} = useContext(AppContext)
	return (
		<Container>
			{currentQuestion.questionOptions
				.map(e => <Response
					key={e.optionId}
					optionId={e.optionId}
					optionString={e.optionString}
				/>)}
		</Container>
	)
}