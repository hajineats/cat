import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import TestContent from "../components/TestContent";
import {useContext, useState} from "react";
import {AppContext} from "../contexts/AppContext";


const Container = styled.div`
  	height: 100vh;
	display: grid;
  	grid-template-rows: 70px 1fr 70px;
`

const TestScreen = ()=>{
	return(
		<Container>
			<TopBar />
			<TestContent>hello</TestContent>
			<BottomBar />
		</Container>
	)
}

export default TestScreen