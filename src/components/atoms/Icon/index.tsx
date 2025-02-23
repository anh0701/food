import React from 'react';
import Image from 'next/image';
import { EIcon } from '@/constants/icon';
interface IconProps {
  icon: EIcon
  alt: string
  width: number
  height: number
}

const Icon: React.FC<IconProps> = ({ icon, alt, width, height }) => {
  return <Image src={icon} alt={alt} width={width} height={height} />
}

export default Icon
