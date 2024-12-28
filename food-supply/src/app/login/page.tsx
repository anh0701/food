"use client";

import { useRouter } from "next/navigation";
import { useEffect, useRef, useState } from "react";

export default function Login() {
    const router = useRouter();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    
    const [errors, setErrors] = useState({
        username: '',
        password: '',
    });

    const passwordInputRef = useRef<HTMLInputElement>(null);

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }

    const regexUsername = /^[A-Za-z0-9].{3,}$/;
    const regexPassword = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;

    async function handleLogin() {
        let validationErrors = {
            username: '',
            password: '',
        };

        if (!regexUsername.test(username)) {
            validationErrors.username = "Username must be at least 4 characters and contain alphanumeric characters.";
        }

        if (!regexPassword.test(password)) {
            validationErrors.password = "Password must contain at least 8 characters, a number, a lowercase letter, an uppercase letter, and a special character.";
        }

        if (validationErrors.username || validationErrors.password) {
            setErrors(validationErrors);
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
            <div className="w-1/4 bg-white rounded-2xl flex flex-col justify-center items-center gap-2 p-8 shadow-md">
                <div className="mb-4 text-center font-bold text-2xl w-full">Login</div>

                    <input 
                        type="text" 
                        placeholder="Username" 
                        className="w-full bg-slate-200 rounded-md p-2 mb-2" 
                        onChange={handleUsernameChange}  
                        onKeyDown={(e) => handleKeyDown(e, passwordInputRef)} 
                    />
                    {errors.username && <div className="text-red-500 text-sm mb-2 break-words whitespace-normal w-full">{errors.username}</div>}

                    <input 
                        type="password" 
                        placeholder="Password" 
                        className="w-full bg-slate-200 rounded-md p-2 mb-2" 
                        ref={passwordInputRef} 
                        onChange={handlePasswordChange} 
                        onKeyDown={handlePasswordKeyDown} 
                    />
                    {errors.password && <div className="text-red-500 text-sm mb-2 break-words whitespace-normal w-full">{errors.password}</div>}

              
                <button 
                    className="w-full p-2 bg-blue-400 rounded-md text-white hover:bg-blue-500 transition-colors duration-300" 
                    onClick={handleLogin}
                >
                    Login
                </button>
            </div>
        </div>
    );
}
