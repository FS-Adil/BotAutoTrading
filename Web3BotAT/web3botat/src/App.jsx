import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

function App() {

  const result = "Крутое приложение!";

  return (
    <>
      <h1>Vite + React = {result} <img src={reactLogo}></img></h1>
    </>
  )
}

export default App
