import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import TestContent from "../components/TestContent";
import {useContext} from "react";
import {AppContext} from "../contexts/AppContext";


const Container = styled.div`
  	height: 100vh;
	display: grid;
  	grid-template-rows: 70px 1fr 70px;
`
const Button = styled.button`
	height: 60px;
  	width: 180px;
  background-color: firebrick;
`

const TestScreen = ()=>{

	const SubmitButton = ()=>{
		const {handleTestSubmission} = useContext(AppContext)
		return(
			<Button onClick={()=>{handleTestSubmission()}} />
		)
	}

	return(
		<Container>
			<TopBar>
				<SubmitButton />
			</TopBar>
			<TestContent>hello</TestContent>
			<BottomBar />
		</Container>
	)
}

export default TestScreen