import styled from "styled-components";
import Question from "./question/Question";
import Sidebar from "./sidebar/Sidebar";
import {useContext, useState} from "react";
import {AppContext} from "../contexts/AppContext";

const Container = styled.div`
	display: grid;
  	grid-template-columns: 200px 1fr;
  	overflow: hidden;
`

const TestContent = ()=>{
	return(
		<Container>
			<Sidebar />
			<Question />
		</Container>
	)
}

export default TestContent