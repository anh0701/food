"use client";

import { useRouter } from "next/navigation";
import { useEffect, useRef, useState } from "react";

export default function Login() {
    const router = useRouter();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const passwordInputRef = useRef<HTMLInputElement>(null);

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }
    const regexUsername = /^[A-Za-z0-9].{3,}$/;
    const regexPassword = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    /**
     * handleLogin
     */
    async function handleLogin() {
        if (!regexUsername.test(username)) {
            alert("username must be ...")
            return;
        }
        if (!regexPassword.test(password)) {
            alert("password must be ...")
            return;
        }

        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        const data = await response.json();
        console.log(data);
        if (response.ok) {
            router.push("/");
        }

    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>, targetRef: React.RefObject<HTMLInputElement | null>) => {
        if (event.key === "Enter") {
            event.preventDefault(); 

            if (targetRef.current) {
                targetRef.current.focus(); 
            }
        }
    };

    const handlePasswordKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === "Enter") {
            event.preventDefault();
            handleLogin(); 
        }
    };

    return (
        <div className="w-screen h-screen flex flex-col justify-center items-center bg-blue-400">
            <div className="bg-white rounded-2xl flex flex-col justify-center items-center gap-2 p-8 shadow-md">
                <div className="mb-4 text-center font-bold text-2xl ">Login</div>
                <input type="text" placeholder="Username" className="w-full bg-slate-200 rounded-md p-2 mb-4" onChange={handleUsernameChange}  onKeyDown={(e) => handleKeyDown(e, passwordInputRef)}/>
                <input type="password" placeholder="Password" className="w-full bg-slate-200 rounded-md p-2 mb-4" ref={passwordInputRef} onChange={handlePasswordChange} onKeyDown={handlePasswordKeyDown}/>
                <button className="w-full p-2 bg-blue-400 rounded-md text-white hover:bg-blue-500 transition-colors duration-300" onClick={handleLogin}>Login</button>
            </div>
        </div>
    );
}