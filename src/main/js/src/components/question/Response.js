import styled, {css} from "styled-components";
import {AppContext} from "../../contexts/AppContext";
import {useContext} from "react";

const Container = styled.div`
  background-color: darkgray;
  padding: 10px;
  ${props => props.responded && css`
    outline: 3px solid black;
  `}
  cursor: pointer;
`
export const Response = ({optionId, optionText, optionImage}) => {
	const {addUserResponse, currentResponse} = useContext(AppContext)
	return (
		<Container
			responded={currentResponse === optionId}
			onClick={()=>{
				addUserResponse(optionId)
			}}
		>
			{optionImage.length != 0 && <img src={`\/images\/${optionImage}`}/>}
			{optionText}
		</Container>
	)

}