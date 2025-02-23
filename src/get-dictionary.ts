import 'server-only'
import type { Locale } from '@/i18n-config'
import { Dictionary } from '@/types/dictionary'

// We enumerate all dictionaries here for better linting and typescript support
// We also get the default import for cleaner types
const dictionaries = {
  en: () => import('@/dictionaries/en').then(module => module.en),
  vi: () => import('@/dictionaries/vi').then(module => module.vi),
}

export const getDictionary = async (locale: Locale) => dictionaries[locale]() as Promise<Dictionary>
