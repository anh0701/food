import React, { useState } from 'react'
import Button from '../../atoms/button'

interface SearchBarProps {
  onSearch: (query: string) => void
  placeholder?: string
  buttonText: string
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearch, placeholder, buttonText }) => {
  const [query, setQuery] = useState('')

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    onSearch(query)
  }

  return (
    <form onSubmit={handleSubmit} className="flex gap-2">
      <input
        type="text"
        value={query}
        onChange={e => setQuery(e.target.value)}
        placeholder={placeholder}
        className="flex-1 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <Button type="submit" variant="primary">
        {buttonText}
      </Button>
    </form>
  )
}

export default SearchBar
