import Banner from "@/components/organisms/banner/page";
import Hero from "@/components/organisms/hero/hero";
import HowItWorks from "@/components/organisms/how-it-works";
import Menu from "@/components/organisms/menu/page";
import PopularItems from "@/components/organisms/popular-items";
import { getDictionary } from "@/get-dictionary";
import { Locale } from "@/i18n-config";

export default async function Home({ params }: { params: { lang: Locale } }) {
  const lang = (await params).lang
  const dictionary = await getDictionary(lang)
  return (
    <div className="w-screen h-screen">
        <Menu />
        <Banner />
      {/* <Hero dictionary={dictionary} />
      <HowItWorks dictionary={dictionary} />
      <PopularItems dictionary={dictionary} /> */}
    </div>
  );
}
