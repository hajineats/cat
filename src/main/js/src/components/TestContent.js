import styled from "styled-components";
import Question from "./Question";
import Sidebar from "./Sidebar";

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