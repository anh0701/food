import Image from 'next/image';

export default function Banner() {
    return (
        <div className="bg-[#FFB30E] w-screen grid grid-cols-3 space-x-4 h-full">

            <div className="w-full col-span-2 p-4 flex flex-col justify-center items-center">

                <div className="inline-block mb-10">
                    <h1 className="font-bold text-white text-6xl mb-2">Are you starving?</h1>
                    <p className="text-sm">Within a few clicks, find meals that are accessible near you</p>
                </div>

                <div className=" bg-white w-2/3 h-200 p-4 rounded-md shadow-md">
                    <div className="flex gap-3 p-3">
                        <div className="flex bg-[#F172281A] shadow-md p-3 rounded-md transition-all duration-300 gap-1">
                            <Image src="/icons/delivery.svg" alt="" width={15} height={15} />
                            <button className=' text-[#F17228]'>Delivery</button>
                        </div>

                        <div className="flex p-3 rounded-md transition-all duration-300 gap-1">
                            <Image src="/icons/pickup.svg" alt="" width={15} height={15} />
                            <button className=' text-[#757575]'>Pickup</button>
                        </div>
                    </div>

                    <hr />

                    <div className='grid grid-cols-3 p-4 space-x-4'>
                        <div className="col-span-2">
                            <input className='bg-slate-100 rounded-md mb-2 w-full p-3' type="text" placeholder='Enter Your Address' />
                        </div>

                        <div className="col-span-1">
                            <div className="flex bg-[#FF7A7A] shadow-md  p-3 rounded-md transition-all duration-300 gap-1">
                                <Image src="/icons/search.svg" alt="" width={15} height={15} />
                                <button className=' text-white'>Find food</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className=" text-white flex items-end">
                <Image
                    src="/images/img-banner.png"
                    alt="Banner"
                    width={250}
                    height={250}
                />
            </div>
        </div>
    );
}