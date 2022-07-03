import styled, {css} from "styled-components";
import {AppContext} from "../../contexts/AppContext";
import {useContext} from "react";

const Container = styled.div`
  background-color: darkgray;
  height: 100px;
  ${props => props.responded && css`
    background-color: #fc8585;
  `}
`
export const Response = ({optionId, optionString}) => {
	const {addUserResponse, currentResponse} = useContext(AppContext)
	return (
		<Container
			responded={currentResponse === optionId}
			onClick={()=>{
				addUserResponse(optionId)
			}}
		>{optionString}</Container>
	)

}