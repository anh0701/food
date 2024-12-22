"use client";
import { Bitter } from "next/font/google";
import { features } from "process";
import React, { useState } from "react";

export default function SignUp(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPass, setConfirmPass] = useState('');
    const [name, setName] = useState<string>();
    const [birthday, setBirthday] = useState('');
    const regexPass = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    const regexUsername = /^[A-Za-z]\w{5,29}$/;

    const handleSignUp = async () => {
        if(!regexPass.test(password)){
            alert("Please enter password must be ....")
        }
        if(!regexUsername.test(username)){
            alert("Username must be ...");
        }
        if(password !== confirmPass){
            alert("password and confirm password must be the same...")
        }

        const response = await fetch( "", {
            method: "POST",
            body: JSON.stringify({username, password, name, birthday})
        });
        const data = await response.json();
        console.log(data);
    }

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }

    const handleBirthdayChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setBirthday(e.target.value);
    }

    return (
        <div className="w-screen h-screen flex flex-col justify-center items-center bg-blue-400">
            <div className="bg-white rounded-2xl flex flex-col justify-center items-center p-8 g-2 shadow-md">
                <div className="font-bold text-2xl mb-4">Sign up</div>
                <input onChange={handleNameChange} type="text" placeholder="Full name" className="rounded-md w-full bg-slate-200 mb-4 p-2"/>
                <input onChange={handlePasswordChange} type="password" placeholder="Password" className="rounded-md w-full bg-slate-200 mb-4 p-2" />
                <input type="password" placeholder="Confirm password" className="rounded-md w-full bg-slate-200 mb-4 p-2" />
                <input onChange={handleBirthdayChange} type="date" name="" id="rounded-md w-full bg-slate-200 mb-4 p-2" />
                <button onClick={handleSignUp} type="button" className="w-full bg-blue-400 rounded-md text-white hover:bg-blue-500 transition-colors duration-300 p-2">Submit</button>
            </div>
        </div>
    );

}