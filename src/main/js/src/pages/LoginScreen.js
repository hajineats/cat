import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import Login from "../components/Login";

const Container = styled.div`
  height: 100vh;
  display: grid;
  grid-template-rows: 70px 1fr 70px;
`

const LoginScreen = () => {
    return (
        <Container>
            <TopBar/>
            <Login />
            <BottomBar/>
        </Container>
    )
}

export default LoginScreen