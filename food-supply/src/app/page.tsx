import Banner from "@/components/banner/page";
import Menu from "@/components/menu/page";

export default function Home() {
  return (
    <div className="w-screen">
      <div className="h-1/10">
        <Menu />
      </div>
      <div className="h-9/10">
        <Banner />
      </div>
    </div>
  );
}
