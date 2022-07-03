import styled from "styled-components";
import {useContext} from "react";
import {AppContext} from "../../contexts/AppContext";

const Container = styled.div`
`
const Header = styled.div`
  background-color: grey;
  height: 70px;
  width: 200px;
  margin-bottom: 10px;
`
const Content = styled.div`
  background-color: grey;
  width: 100%;
  height: 200px;
`
export const QuestionArea = () => {
	const {title, content} = useContext(AppContext)

	return (
		<Container>
			<Header>
				{title}
			</Header>
			<Content>
				{content}
			</Content>
		</Container>
	)
}