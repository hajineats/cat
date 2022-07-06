import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import {useContext} from "react";
import {AppContext} from "../contexts/AppContext";

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
    const {beginTest, instructions} = useContext(AppContext)

    return (
        <Container>
            <TopBar/>
            <Content>
                <Instructions>
                    {instructions}
                </Instructions>
            </Content>

            <BottomBar>
                <ConfirmInput onClick={(e) => {
                    e.preventDefault();
                    beginTest();
                }}>
                    Confirm
                </ConfirmInput>
            </BottomBar>
        </Container>
    )
}

export default InstructionScreen