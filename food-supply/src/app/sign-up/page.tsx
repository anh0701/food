"use client";
import React, { useState } from "react";

export default function SignUp() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPass, setConfirmPass] = useState('');
    const [name, setName] = useState<string>();
    const [birthday, setBirthday] = useState('');
    
    const [errors, setErrors] = useState({
        username: '',
        password: '',
        confirmPass: '',
    });

    const regexPass = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;
    const regexUsername = /^[A-Za-z]\w{5,29}$/;

    const handleSignUp = async () => {
        let validationErrors = {
            username: '',
            password: '',
            confirmPass: '',
        };

        if (!regexPass.test(password)) {
            validationErrors.password = "Password must be at least 8 characters, include a number, a lowercase letter, an uppercase letter, and a special character.";
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

        const response = await fetch("", {
            method: "POST",
            body: JSON.stringify({ username, password, name, birthday })
        });

        const data = await response.json();
        console.log(data);
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

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }

    const handleBirthdayChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setBirthday(e.target.value);
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

                <div className="w-full">
                    <label className="block text-sm font-semibold">Birthday</label>
                    <input 
                        onChange={handleBirthdayChange} 
                        type="date" 
                        name="date" 
                        className="rounded-md w-full bg-slate-200 mb-4 p-2" 
                    />
                </div>

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
