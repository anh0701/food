"use client";

import { Dictionary } from "@/types/dictionary";
import React from "react";

interface FoodItem {
  id: number;
  name: string;
  image: string;
  price: number;
  restaurant: string;
  rating: number;
  deliveryTime: string;
}

const PopularItems: React.FC<{ dictionary: Dictionary }> =  ({
  dictionary,
}) => {
  const items: FoodItem[] = [
    // Add your food items here
  ];

  return (
    <section className="py-20 bg-gray-50">
      <div className="container mx-auto px-4">
        <h2 className="text-4xl font-bold text-center mb-16">
          {dictionary.popularItems.title}
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {items.map((item) => (
            <div
              key={item.id}
              className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow"
            >
              <img
                src={item.image}
                alt={item.name}
                className="w-full h-48 object-cover"
              />
              <div className="p-4">
                <h3 className="font-semibold text-lg mb-2">{item.name}</h3>
                <div className="flex items-center justify-between mb-2">
                  <span className="text-yellow-500">⭐ {item.rating}</span>
                  <span className="text-gray-500">{item.deliveryTime}</span>
                </div>
                <div className="flex items-center justify-between">
                  <span className="font-bold text-xl">
                    ${item.price.toFixed(2)}
                  </span>
                  <button className="bg-yellow-500 text-white px-4 py-2 rounded-full hover:bg-yellow-600">
                    {dictionary.common.orderNow}
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default PopularItems;
