import {useTimer} from "react-timer-hook";
import styled from "styled-components";
import {useContext} from "react";
import {AppContext} from "../../contexts/AppContext";


const Container = styled.div`
  display: flex;
  align-items: center;
  background-color: lightgrey;
  width: 14em;
  justify-content: center;
`

const Timer = ({timeRemainingInSeconds})=>{
	const {handleTestSubmission} = useContext(AppContext)
	const time = new Date()
	time.setSeconds(time.getSeconds() + timeRemainingInSeconds)

	const fixToDigits = (numOfDigits, text)=>{
		return text.toLocaleString('en-US', {
			minimumIntegerDigits: 2,
			useGrouping: false
		})
	}

	const {seconds, minutes, hours} = useTimer({expiryTimestamp: time, onExpire: ()=>handleTestSubmission()})
	return (<Container>
		Time remaining {fixToDigits(2,minutes)}:{fixToDigits( 2, seconds)}
	</Container>)
}

export default Timer