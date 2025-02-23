"use client";
import { Dictionary } from "@/types/dictionary";
import React from "react";

const steps = [
  {
    icon: "🔍",
    title: "Select location",
    description: "Choose the location where your food will be delivered.",
  },
  {
    icon: "🍽️",
    title: "Choose order",
    description: "Check over hundreds of menus to pick your favorite food",
  },
  {
    icon: "💳",
    title: "Pay advanced",
    description: "It's quick, safe, and simple. Select several payment methods",
  },
  {
    icon: "🚚",
    title: "Enjoy meals",
    description: "Food is made and delivered directly to your home.",
  },
];

const HowItWorks: React.FC<{ dictionary: Dictionary }> =  ({
  dictionary,
}) => {
  return (
    <section className="py-20 bg-white">
      <div className="container mx-auto px-4">
        <h2 className="text-4xl font-bold text-center mb-16">
          {dictionary.howItWorks.title}
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {steps.map((step, index) => (
            <div key={index} className="text-center">
              <div className="w-20 h-20 mx-auto mb-4 bg-yellow-100 rounded-full flex items-center justify-center text-3xl">
                {step.icon}
              </div>
              <h3 className="text-xl font-semibold mb-2">
                {dictionary.howItWorks.steps[index].title}
              </h3>
              <p className="text-gray-600">
                {dictionary.howItWorks.steps[index].description}
              </p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default HowItWorks;
