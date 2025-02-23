import { Dictionary } from '@/types/dictionary'

export const vi: Dictionary = {
  common: {
    loading: 'Đang tải dữ liệu...',
    search: 'Tìm kiếm',
    searchPlaceholder: 'Nhập tên sản phẩm cần tìm...',
    orderNow: 'Đặt Ngay',
  },
  header: {
    title: 'Hệ Thống Quản Lý Cung Ứng Thực Phẩm',
  },
  footer: {
    copyright: '© 2024 CSND Food Supply. Bản quyền thuộc về Công an Nhân dân.',
  },
  product: {
    price: 'Giá',
    quantity: 'Số lượng tồn',
    errors: {
      fetchError: 'Lỗi khi tải danh sách sản phẩm',
      searchError: 'Lỗi khi tìm kiếm sản phẩm',
    },
  },
  search: {
    placeholder: 'Tìm kiếm...',
    button: 'Tìm',
  },
  dashboard: {
    header: 'Bảng Điều Khiển',
    sidebar: 'Thanh Điều Hướng',
    footer: 'Chân Trang',
    loading: 'Đang tải...',
    error: 'Có lỗi xảy ra',
  },
  button: {
    primary: 'Nút Chính',
    secondary: 'Nút Phụ',
  },
  hero: {
    title: 'Đặt Món Ăn Yêu Thích Của Bạn',
    subtitle: 'Giao hàng nhanh chóng đến tận nhà bạn',
    searchPlaceholder: 'Nhập địa chỉ của bạn',
    searchButton: 'Tìm Kiếm',
  },
  howItWorks: {
    title: 'Cách Thức Hoạt Động',
    steps: {
      '0': {
        title: 'Chọn Địa Điểm',
        description: 'Chọn vị trí bạn muốn giao hàng',
      },
      '1': {
        title: 'Chọn Món Ăn',
        description: 'Lựa chọn từ hàng trăm menu yêu thích',
      },
      '2': {
        title: 'Thanh Toán',
        description: 'Nhanh chóng, an toàn với nhiều phương thức',
      },
      '3': {
        title: 'Thưởng Thức',
        description: 'Món ăn được giao trực tiếp đến nhà bạn',
      },
    },
  },
  popularItems: {
    title: 'Món Ăn Phổ Biến',
  },
}
