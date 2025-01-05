"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import Image from 'next/image';

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
        <div className="p-4 shadow-lg w-screen h-full">
            <div className="max-w-6xl mx-auto flex justify-between items-center">

                <div className="flex gap-3 justify-center items-center">

                    <Image src="/images/logo.svg"
                        alt="Logo"
                        width={30}
                        height={30}
                    />
                    <p className="text-[#F17228]">food<strong className="text-[#FFB30E]">wagon</strong></p>

                    {/* <button
                        className=" text-white hover:bg-blue-700 p-3 rounded-md transition-all duration-300"
                        onClick={goHome} >
                        Home
                    </button> */}

                </div>
                <div className="flex items-center">
                    <strong className="flex items-center text-sm">
                        Deliver to:
                        <span className="flex items-center ml-1">
                            <Image src="/icons/map-marker-alt.svg" alt="Location" width={15} height={15} />
                            <span className="font-normal ml-1">Current Location</span>
                        </span>
                        &nbsp;
                        Mohammadpur Bus Stand, Dhaka
                    </strong>
                </div>

                <div className="flex gap-4">
                    <div className="flex items-center ml-1">
                        <Image
                            src="/icons/search.svg"
                            alt="Search"
                            width={15}
                            height={15}
                        />
                        <strong className="text-sm ml-1">Search food</strong>
                    </div>
                    <div className="flex shadow-md shadow-[#FF8A00] hover:bg-orange-100 p-3 rounded-md transition-all duration-300 gap-1">
                        <Image src="/icons/user.svg" alt="" width={15} height={15} />
                        <button
                            className=" text-sm text-[#FF8A00]"
                            onClick={handleLogin}>
                            Login
                        </button>
                    </div>

                    {/* <div className="flex shadow-md shadow-[#FF8A00] hover:bg-orange-100 p-3 rounded-md transition-all duration-300 gap-1">
                        <Image src="/icons/user.svg" alt="" width={15} height={15} />
                        <button
                            className=" text-sm text-[#FF8A00]"
                            onClick={handleSignUp}>
                            Sign Up
                        </button>
                    </div> */}

                    {/* <button
                        className="text-white hover:bg-blue-700 p-3 rounded-md transition-all duration-300"
                        onClick={handleLogout}>
                        Logout
                    </button> */}
                </div>
            </div>
        </div>
    );
}