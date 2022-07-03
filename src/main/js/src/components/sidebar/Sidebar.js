import styled from "styled-components";
import {SidebarItem} from "./SidebarItem";
import {useContext} from "react";
import {AppContext} from "../../contexts/AppContext";

const Container = styled.div`
  background-color: #61dafb;
  overflow-y: scroll;
`
const Sidebar = ()=>{
	const {currentQuestions} = useContext(AppContext)

	return (
		<Container>
			{currentQuestions.map((e)=><SidebarItem
				key={e.id}
				question={e} />)}
		</Container>
	)
}


export default Sidebar