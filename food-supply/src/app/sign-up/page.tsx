"use client";
import { useRouter } from "next/navigation";
import React, { useState } from "react";

export default function SignUp() {
    const router = useRouter();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPass, setConfirmPass] = useState('');
    const [name, setName] = useState<string>();
    const [email, setEmail] = useState<string>('');
    
    const [errors, setErrors] = useState({
        username: '',
        email: '',
        password: '',
        confirmPass: '',
    });

    const regexPass = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    const regexUsername = /^[A-Za-z]\w{5,29}$/;
    const regexEmail = /^[\w\.-]+@[a-zA-Z\d\.-]+\.[a-zA-Z]{2,6}$/;

    const handleSignUp = async () => {
        let validationErrors = {
            username: '',
            email: '',
            password: '',
            confirmPass: '',
        };

        if (!regexPass.test(password)) {
            validationErrors.password = "Password must be at least 8 characters, include a number, a lowercase letter, an uppercase letter, and a special character.";
        }

        if(!regexEmail.test(email)){
            validationErrors.email = "Email ";
        }

        if (!regexUsername.test(username)) {
            validationErrors.username = "Username must start with a letter and be between 6 and 30 characters.";
        }

        if (password !== confirmPass) {
            validationErrors.confirmPass = "Password and confirm password must be the same.";
        }

        if (validationErrors.username || validationErrors.password || validationErrors.confirmPass) {
            setErrors(validationErrors);
            return; 
        }

        const response = await fetch("http://localhost:8080/auth/sign-up", {
            method: "POST",
            body: JSON.stringify({ username, password, email, name })
        });

        const data = await response.json();
        console.log(data);

        if (response.ok) {
            router.push("/");  
        }
    }

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const handleConfirmPasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setConfirmPass(e.target.value);
    }

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
    }

    const handleUserNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }

    return (
        <div className="w-screen h-screen flex flex-col justify-center items-center bg-blue-400">
            <div className="w-1/4 bg-white rounded-2xl flex flex-col justify-center items-center p-8 g-2 shadow-md">
                <div className="font-bold text-2xl mb-4 w-full text-center">Sign up</div>
                <input 
                    onChange={handleNameChange} 
                    type="text" 
                    placeholder="Full name" 
                    className="rounded-md w-full bg-slate-200 mb-4 p-2" 
                />
                <input 
                    onChange={handleUserNameChange} 
                    type="text" 
                    placeholder="Username" 
                    className="rounded-md w-full bg-slate-200 mb-4 p-2" 
                />
                <input 
                    onChange={handlePasswordChange} 
                    type="password" 
                    placeholder="Password" 
                    className="rounded-md w-full bg-slate-200 mb-4 p-2" 
                />
                {errors.password && <div className="text-red-500 text-sm mb-4 break-words whitespace-normal w-full">{errors.password}</div>}

                <input 
                    onChange={handleConfirmPasswordChange} 
                    type="password" 
                    placeholder="Confirm password" 
                    className="rounded-md w-full bg-slate-200 mb-4 p-2" 
                />
                {errors.confirmPass && <div className="text-red-500 text-sm mb-4 break-words whitespace-normal w-full">{errors.confirmPass}</div>}

                <input 
                    onChange={handleEmailChange} 
                    type="email" 
                    placeholder="Email" 
                    className="rounded-md w-full bg-slate-200 mb-4 p-2" 
                />
                {errors.email && <div className="text-red-500 text-sm mb-4 break-words whitespace-normal w-full">{errors.email}</div>}


                <button 
                    onClick={handleSignUp} 
                    type="button" 
                    className="w-full bg-blue-400 rounded-md text-white hover:bg-blue-500 transition-colors duration-300 p-2"
                >
                    Submit
                </button>
            </div>
        </div>
    );
}
