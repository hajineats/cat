import {useTimer} from "react-timer-hook";
import styled from "styled-components";
import {useContext, useEffect} from "react";
import {AppContext} from "../../contexts/AppContext";
import usePersistedState from "../hooks/usePersistedState";


const Container = styled.div`
  display: flex;
  align-items: center;
  background-color: lightgrey;
  width: 14em;
  justify-content: center;
`

const Timer = ({timeRemainingInSeconds})=>{
	const {handleTestSubmission} = useContext(AppContext)
	// cache timeRemaining
	const [timeRemaining, setTimeRemaining] = usePersistedState("time",timeRemainingInSeconds)

	const time = new Date()
	time.setSeconds(time.getSeconds() + timeRemaining)
	const {seconds, minutes, hours} = useTimer({expiryTimestamp: time, onExpire: ()=>handleTestSubmission()})

	useEffect(()=>{
		setTimeRemaining(seconds+minutes*60)
	},[seconds])



	const fixToDigits = (numOfDigits, text)=>{
		return text.toLocaleString('en-US', {
			minimumIntegerDigits: 2,
			useGrouping: false
		})
	}


	return (<Container>
		Time remaining {fixToDigits(2,minutes)}:{fixToDigits( 2, seconds)}
	</Container>)
}

export default Timer