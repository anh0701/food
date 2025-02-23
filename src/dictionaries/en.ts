import { Dictionary } from '@/types/dictionary'

export const en: Dictionary = {
  common: {
    loading: 'Loading...',
    search: 'Search',
    searchPlaceholder: 'Enter product name to search...',
    orderNow: 'Order Now',
  },
  header: {
    title: 'Food Supply Management System',
  },
  footer: {
    copyright: '© 2024 CSND Food Supply. All rights reserved.',
  },
  product: {
    price: 'Price',
    quantity: 'Stock',
    errors: {
      fetchError: 'Error fetching products',
      searchError: 'Error searching products',
    },
  },
  search: {
    placeholder: 'Search for products...',
    button: 'Search',
  },
  dashboard: {
    header: 'Dashboard',
    sidebar: 'Sidebar',
    footer: 'Footer',
    loading: 'Loading...',
    error: 'Error',
  },
  button: {
    primary: 'Primary',
    secondary: 'Secondary',
  },
  hero: {
    title: 'Food Supply Management System',
    subtitle: 'Efficiently manage your food supply chain with our system',
    searchPlaceholder: 'Search for products...',
    searchButton: 'Search',
  },
  howItWorks: {
    title: 'How It Works',
    steps: {
      '0': {
        title: 'Step 1',
        description: 'Description of step 1',
      },
      '1': {
        title: 'Step 2',
        description: 'Description of step 2',
      },
      '2': {
        title: 'Step 3',
        description: 'Description of step 3',
      },
      '3': {
        title: 'Step 4',
        description: 'Description of step 4',
      },
    },
  },
  popularItems: {
    title: 'Popular Items',
  },
}
