import styled from "styled-components";
import {useContext, useState} from "react";
import {AppContext} from "../../contexts/AppContext";
import {QuestionArea} from "./QuestionArea";
import {ResponseArea} from "./ResponseArea";

const Container = styled.div`
  padding: 20px;
  display: grid;
  gap: 10px;
  justify-content: stretch;
  align-content: start;
  overflow-y: scroll;
`

const Question = () => {
	return (
		<Container>
			<QuestionArea/>
			<ResponseArea />
		</Container>
	)
}

export default Question

