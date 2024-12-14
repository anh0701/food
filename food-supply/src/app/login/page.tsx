"use client";

import { useState } from "react";

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }
    const regexUsername = /^[A-Za-z]\w{5,29}$/;
    const regexPassword = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    /**
     * hanleLogin
     */
    async function hanleLogin() {
        if(!regexUsername.test(username)){
            alert("username must be ...")
            return;
        }
        if(!regexPassword.test(password)){
            alert("password must be ...")
            return;
        }
        const response = await fetch("",{
            method: "POST",
            body: JSON.stringify({username, password})
        });
        const data = await response.json();
        console.log(data);
    }
    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }
    return (
        <div className="w-screen h-screen flex flex-col justify-center items-center bg-blue-400">
            <div className="bg-white rounded-2xl flex flex-col justify-center items-center gap-2 p-8 shadow-md">
                <div className="mb-4 text-center font-bold text-2xl ">Login</div>
                <input type="text" placeholder="Username" className="w-full bg-slate-200 rounded-md p-2 mb-4" onChange={handleUsernameChange} />
                <input type="password" placeholder="Password" className="w-full bg-slate-200 rounded-md p-2 mb-4" onChange={handlePasswordChange} />
                <button className="w-full p-2 bg-blue-400 rounded-md text-white hover:bg-blue-500 transition-colors duration-300" onClick={hanleLogin}>Login</button>
            </div>
        </div>
    );
}