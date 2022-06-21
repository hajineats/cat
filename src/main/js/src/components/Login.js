import styled from "styled-components";

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

const SubmitInput = styled.input`
  height: 32px;
  width: 84px;
`

const Login = () => {
    return (
        <Container>
            <Header></Header>
            <Form>
                <TextInput type="text"/>
                <SubmitInput type="submit"/>
            </Form>
        </Container>
    )
}

export default Login