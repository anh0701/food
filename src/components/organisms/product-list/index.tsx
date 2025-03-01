'use client'
import { getDictionary } from '@/get-dictionary'
import { Locale } from '@/i18n-config'
import React, { useEffect, useState } from 'react'
import SearchBar from '../../molecules/search-bar'

interface Product {
  id: number
  name: string
  price: number
  unit: string
  quantity: number
}

const ProductList: React.FC<{ lang: Locale }> = async ({ lang }) => {
  const dictionary = await getDictionary(lang);
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const [products, setProducts] = useState<Product[]>([]);
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const [loading, setLoading] = useState(true);
  // eslint-disable-next-line react-hooks/rules-of-hooks
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch('/api/products')
        const data = await response.json()
        setProducts(data)
      } catch (error) {
        console.error(dictionary.product.errors.fetchError, error)
      } finally {
        setLoading(false)
      }
    }

    fetchProducts()
  }, [lang])

  const handleSearch = async (query: string) => {
    setLoading(true)
    try {
      const response = await fetch(`/api/products/search?q=${query}`)
      const data = await response.json()
      setProducts(data)
    } catch (error) {
      console.error(dictionary.product.errors.searchError, error)
    } finally {
      setLoading(false)
    }
  }

  if (loading) {
    return <div className="text-center py-4">{dictionary.common.loading}</div>
  }

  return (
    <div className="space-y-4">
      <SearchBar
        onSearch={handleSearch}
        placeholder={dictionary.common.searchPlaceholder}
        buttonText={dictionary.common.search}
      />
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {products.map(product => (
          <div
            key={product.id}
            className="p-4 border rounded-md shadow-sm hover:shadow-md transition-shadow"
          >
            <h3 className="font-medium">{product.name}</h3>
            <div className="mt-2 text-gray-600">
              <p>
                {dictionary.product.price}: {product.price.toLocaleString()} VNĐ/
                {product.unit}
              </p>
              <p>
                {dictionary.product.quantity}: {product.quantity} {product.unit}
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default ProductList
