import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import {useContext} from "react";
import {AppContext} from "../contexts/AppContext";
import {useLocation} from "react-router-dom";

const Container = styled.div`
  height: 100vh;
  display: grid;
  grid-template-rows: 70px 1fr 70px;
`

const Content = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const Instructions = styled.div`
  text-align: center;
  flex: 0 0 600px;
`

const ConfirmInput = styled.button`
  height: 32px;
  width: 96px;
`

const InstructionScreen = () => {
    const {beginTest} = useContext(AppContext)

    const {state} = useLocation()

    return (
        <Container>
            <TopBar/>
            <Content>
                <Instructions>
                    {state.msg}
                </Instructions>
            </Content>

            <BottomBar>
                <ConfirmInput onClick={(e) => {
                    e.preventDefault();
                    beginTest();
                }}>
                    Begin Test
                </ConfirmInput>
            </BottomBar>
        </Container>
    )
}

export default InstructionScreen