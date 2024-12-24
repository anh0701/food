"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

export default function Menu() {
    const router = useRouter();
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLogout = () => {
        setIsLoggedIn(false);
        // 
        alert("You have logged out!");
        router.push("/");
    };

    const handleLogin = () => {
        setIsLoggedIn(true);
        router.push("/login");
    };

    const handleSignUp = () => {
        router.push("/sign-up");
    };

    const goHome = () => {
        router.push("/");
    };

    return (
        <div className="bg-blue-600 p-4 shadow-lg">
            <div className="max-w-6xl mx-auto flex justify-between items-center">

                <div className="flex ">
                    <button
                        className=" text-white hover:bg-blue-700 p-3 rounded-md transition-all duration-300"
                        onClick={goHome} >
                        Home
                    </button>

                    <button
                        className="text-white hover:bg-blue-700 p-3 rounded-md transition-all duration-300"
                        onClick={handleLogin}>
                        Login
                    </button>
                    <button
                        className="text-white hover:bg-blue-700 p-3 rounded-md transition-all duration-300"
                        onClick={handleSignUp}>
                        Sign Up
                    </button>

                    <button
                        className="text-white hover:bg-blue-700 p-3 rounded-md transition-all duration-300"
                        onClick={handleLogout}>
                        Logout
                    </button>

                </div>
            </div>
        </div>
    );
}