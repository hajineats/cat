import styled from "styled-components";


const Container = styled.div`
  	display: flex;
	background-color: #D9D9D9;
  justify-content: flex-end;
`

const TopBar = ({children})=>{
	return(
		<Container>
			{children}
		</Container>
	)
}

export default TopBar