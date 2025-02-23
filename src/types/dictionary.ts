export interface Dictionary {
  common: {
    loading: string;
    search: string;
    searchPlaceholder: string;
    orderNow: string;
  };
  header: {
    title: string;
  };
  footer: {
    copyright: string;
  };
  product: {
    price: string;
    quantity: string;
    errors: {
      fetchError: string;
      searchError: string;
    };
  };
  search: {
    placeholder: string;
    button: string;
  };
  dashboard: {
    header: string;
    sidebar: string;
    footer: string;
    loading: string;
    error: string;
  };
  button: {
    primary: string;
    secondary: string;
  };
  hero: {
    title: string;
    subtitle: string;
    searchPlaceholder: string;
    searchButton: string;
  };
  howItWorks: {
    title: string;
    steps: {
      [key: number]: {
        title: string;
        description: string;
      };
    };
  };
  popularItems: {
    title: string;
  };
}

// Helper type để lấy tất cả các đường dẫn có thể có trong Dictionary
export type DictionaryKeys = {
  [K in keyof Dictionary]: Dictionary[K] extends string
    ? K
    : {
        [P in keyof Dictionary[K]]: Dictionary[K][P] extends string
          ? `${K & string}.${P & string}`
          : {
              [Q in keyof Dictionary[K][P]]: `${K & string}.${P & string}.${Q &
                string}`;
            }[keyof Dictionary[K][P]];
      }[keyof Dictionary[K]];
}[keyof Dictionary]; 