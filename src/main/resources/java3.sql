-- CREATE DATABASE SOF203_BVASM;
USE SOF203_BVASM;

DROP TABLE IF EXISTS sach;
CREATE TABLE sach (
    Masach VARCHAR(5) PRIMARY KEY,
    Tensach NVARCHAR(50),
    Gia FLOAT,
    Namxb INT,
    Loai VARCHAR(5)
);

INSERT INTO sach (Masach, Tensach, Gia, Namxb, Loai) VALUES 
    ('sh01', 'Tho Xuan Dieu', 600, 2019, 'vh'),
    ('sh02', 'Lap Trinh C', 45.6, 2019, 'kh'),
    ('sh03', 'Hon dat', 123, 2019, 'vh'),
    ('sh04', 'Bai tap Java', 150, 2020, 'kh'),
    ('sh05', 'Python', 34, 2022, 'kh'),
    ('sh06', 'Binh minh mua', 200, 2023, 'vh'),
    ('sh07', 'Truyen cuoi', 345, 2018, 'vh'),
    ('sh08', 'C#', 340, 2023, 'kh');

SELECT * FROM sach
--------------------------------------------------------------------------
drop table if exists sanpham;
create table sanpham (
     masp varchar(4) primary key,
     tensp nvarchar(50),
     gia float,
     loaisp nvarchar(50)
);

insert into sanpham values
    ('sp01', 'Bánh Kinh Đô', 45000, 'Bánh kẹo'),
    ('sp02', 'Bia Tiger', 20000, 'Rượu bia'),
    ('sp03', 'Cá hồi', 300, 'Hải sản');

select * from sanpham