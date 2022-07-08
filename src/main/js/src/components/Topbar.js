import styled from "styled-components";

const Container = styled.div`
	background-color: #D9D9D9;
`

const TopBar = ({children})=>{
	return(
		<Container>
			{children}
		</Container>
	)
}

export default TopBar