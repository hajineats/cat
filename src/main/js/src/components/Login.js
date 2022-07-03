import styled from "styled-components";
import {Link} from "react-router-dom";
import {useContext, useState} from "react";
import {AppContext} from "../contexts/AppContext";

const Container = styled.div`
  margin: auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 300px;
  border: 2px solid black;
  background-color: lightgrey;
`

const Header = styled.div`
  background-color: grey;
  height: 70px;
  width: 200px;
`

const Form = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`

const TextInput = styled.input`
  font-size: 20px;
  margin: 24px 0px 24px;
  padding: 12px 20px;
`

const SubmitInput = styled.button`
  height: 32px;
  width: 84px;
`

const Login = () => {
    const {handleLogin} = useContext(AppContext)
    const [userInput, setUserInput] = useState("")

    return (
        <Container>
            <Header></Header>
            <Form>
                <TextInput type="text" onChange={(e)=>{
                    setUserInput(e.target.value)
                }} />
                <SubmitInput onClick={(e)=>{
                    e.preventDefault()
                    handleLogin(userInput)}}/>
            </Form>
        </Container>
    )
}

export default Login