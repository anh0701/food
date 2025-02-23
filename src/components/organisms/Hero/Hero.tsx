"use client";

import React from "react";
import SearchBar from "@/components/molecules/SearchBar";
import { Dictionary } from "@/types/dictionary";

const Hero: React.FC<{ dictionary: Dictionary }> =  ({ dictionary }) => {

  const handleSearch = (location: string) => {
    // Handle location search
    console.log("Searching for location:", location);
  };

  return (
    <div className="relative bg-yellow-50">
        <div className="container mx-auto px-4 py-20">
          <div className="max-w-2xl">
            <h1 className="text-6xl font-bold mb-6">{dictionary.hero.title}</h1>
          <p className="text-xl mb-8 text-gray-600">{dictionary.hero.subtitle}</p>
          <SearchBar
            onSearch={handleSearch}
            placeholder={dictionary.hero.searchPlaceholder}
            buttonText={dictionary.hero.searchButton}
          />
        </div>
        <div className="absolute right-0 top-0 w-1/2 h-full">
          <img
            src="/images/hero-image.png"
            alt="Food delivery"
            className="object-cover h-full w-full"
          />
        </div>
      </div>
    </div>
  );
};

export default Hero; 