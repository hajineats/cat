import styled from "styled-components";
import {AppContext} from "../../contexts/AppContext";
import {useContext, useState} from "react";

const Container = styled.div`
  background-color: darkgray;
  height: 100px;
`

const TextInput = styled.textarea`
  font-family: inherit;
  font-size: 16px;
`

export const TextResponse = () => {
    const {addUserResponse, currentResponse} = useContext(AppContext)
    const [, setInput] = useState(currentResponse || '');

    return (

            <TextInput type="text" value={currentResponse || ''} onChange={(e) => {
                setInput(e.target.value);
                addUserResponse(e.target.value);
            }}/>


    )
}