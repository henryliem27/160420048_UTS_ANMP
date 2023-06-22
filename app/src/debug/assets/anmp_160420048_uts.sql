PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO android_metadata VALUES('en_US');
CREATE TABLE `doctors` (`doctor_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `profesi` TEXT, `pengalaman_kerja` INTEGER NOT NULL, `alumnus` TEXT, `pratik_di` TEXT NOT NULL, `nomor_str` TEXT NOT NULL, `rating` REAL NOT NULL, `photo_url` TEXT NOT NULL);
INSERT INTO doctors VALUES(1,'Dr. Hamdani Chrisyanto','Doker Umum',21,'Universitas Katolik Indonesia Atma Jaya, 2000','Jawa Barat, Bogor','8545600212174037',96.0,'https://d1e8la4lqf1h28.cloudfront.net/thumbnails/08879dca-b8d3-48e3-addc-bc564718c64f_doctor_thumbnail_url.webp');
INSERT INTO doctors VALUES(2,'Dr. Vania Angela','Dokter Umum',8,'Universitas Kristen Krida Wacana, 2014','Tangerang Selatan, Banten','1311100117174037',99.0,'https://d1e8la4lqf1h28.cloudfront.net/thumbnails/36f88432-3684-4d05-b96b-aa21091bbeb1_doctor_thumbnail_url.webp');
INSERT INTO doctors VALUES(3,'Dr. Muhammad Ikhsan Chan','Dokter Umum',4,'Universitas Sumatera Utara, 2017','Bekasi, Jawa Barat','1221100119204454',99.0,'https://d1e8la4lqf1h28.cloudfront.net/thumbnails/8b79599e-1b6d-4280-ba92-fd757450999f_doctor_thumbnail_url.webp');
INSERT INTO doctors VALUES(4,'Dr. Eka Gunawijaya Sp.A(K)','Sp.Anak',30,'Universitas Udayana, 1994\r\nUniversitas Indonesia, 2011','Denpasar, Bali','5111201421047157',100.0,'https://d1e8la4lqf1h28.cloudfront.net/images/212536_11-4-2023_11-34-58.jpeg');
INSERT INTO doctors VALUES(5,'Dr. Vincencius William M.Sc, Sp.A','Sp.Anak',8,'Unika Atma Jaya Jakarta, 2015\r\nUniversitas Gadjah Mada, 2022','Kota Palembang, Sumatera Selatan','3411201322164411',99.0,'https://d1e8la4lqf1h28.cloudfront.net/images/756691_30-1-2023_23-11-42.jpeg');
INSERT INTO doctors VALUES(6,'Dr. Adelien Sp.THT-KL(K)','sp.THT',19,'Universitas Sriwijaya, 2013\r\nUniversitas Sriwijaya, 2004','Kemuning, Palembang','1621606423031560',97.0,'https://d1e8la4lqf1h28.cloudfront.net/thumbnails/94cd45ac-1ea7-43d6-bb51-672f7dad15fc_doctor_thumbnail_url.webp');
INSERT INTO doctors VALUES(7,'Dr. Muhammad Anwar Sp.THT-KL','sp.THT',10,'Universitas Hasanuddin, 2011\r\nUniversitas Hasanuddin, 2022','Kab. Pulau Morotai, Maluku Utara','7311606322129925',95.0,'https://d1e8la4lqf1h28.cloudfront.net/images/659594_28-9-2022_10-38-54.jpeg');
INSERT INTO doctors VALUES(8,'Dr. Putu Dian Anggreni Setiawan Sp','Sp. THT',11,'Universitas Kristen Indonesia , 2012\r\nUniversitas Udayana, 2021','Badung, Bali','5121606321132752',94.0,'https://d1e8la4lqf1h28.cloudfront.net/images/625000_5-7-2022_10-3-43.jpeg');
CREATE TABLE `jadwal` (`jadwal_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `doctor_id` INTEGER NOT NULL, `lokasi` TEXT, `waktu` TEXT NOT NULL, `tanggal` TEXT);
INSERT INTO jadwal VALUES(1,1,'Jl. DR. Sumeru No.114, RT.02/RW.01, Menteng, Kec. Bogor Bar., Kota Bogor, Jawa Barat 16111','6:00 PM','12 Mei 2023');
INSERT INTO jadwal VALUES(2,2,'Jl. Pajajaran No.101, Pamulang Bar., Kec. Pamulang, Kota Tangerang Selatan, Banten 15417','6:00 PM','15 Mei 2023');
INSERT INTO jadwal VALUES(3,3,'Jl. Raya Seroja No.19, RT.002/RW.028, Harapan Jaya, Kec. Bekasi Utara, Kota Bks, Jawa Barat 17124','6:00 PM','12 April 2023');
INSERT INTO jadwal VALUES(4,4,'Jl. Mahendradatta No.57 X, Padangsambian, Kec. Denpasar Bar., Kota Denpasar, Bali 80119','7:00 PM','11 Mei 2023');
INSERT INTO jadwal VALUES(5,5,'Jl. Jend. Sudirman No.1051, 20 Ilir D. I, Kec. Ilir Tim. I, Kota Palembang, Sumatera Selatan 30114','9:00 PM','23 Mei 2023');
INSERT INTO jadwal VALUES(6,6,' 2QM2+V8F, Sekip Jaya, Kec. Kemuning, Kota Palembang, Sumatera Selatan 30114','9:00 PM','23 Mei 2023');
INSERT INTO jadwal VALUES(7,7,'Dehegila, Kec. Morotai Sel., Kabupaten Pulau Morotai, Maluku Utara 97771','9:00 PM','24 Mei 2023');
INSERT INTO jadwal VALUES(8,8,'Jalan Raya Kapal, Mangupura, Mengwi, Kapal, Kec. Mengwi, Kabupaten Badung, Bali 80351','5:00 PM','23 Mei 2023');
CREATE TABLE `user` (`user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `username` TEXT, `password` TEXT, `photoUrl` TEXT NOT NULL);
INSERT INTO user VALUES(1,'xiaoting','pancake063','pancake063','https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
CREATE TABLE `obat` (`obat_id` INTEGER PRIMARY KEY AUTOINCREMENT, `nama` TEXT, `perbuah` TEXT NOT NULL, `harga` TEXT, `deskripsi` TEXT NOT NULL, `indikasi_umum` TEXT, `komposisi` TEXT, `dosis` TEXT, `aturan_pakai` TEXT, `golongan_produk` TEXT, `kemasan` TEXT, `manufaktur` TEXT, `no_regristasi` TEXT, `photo_url` TEXT);
INSERT INTO obat VALUES(1,'Fluimucil 600 mg 2 Tablet Effervescent','Per Strip','Rp22.200 - Rp41.500','FLUIMUCIL Effervescent 600 mengandung zat aktif Acetylcysteine 600 mg, yang diindikasikan sebagai obat batuk-mukolitik bekerja dengan mengencerkan dahak dan mengaktifkan sistem silia pembersihan paru. Dalam penggunaan obat ini HARUS SESUAI DENGAN PETUNJUK DOKTER.','INFORMASI OBAT INI HANYA UNTUK KALANGAN MEDIS. Sebagai treatment penyakit saluran pernafasan yang ditandai dengan hipersekresi mukus yang tebal dan kental. FLUIMUCIL Effervescent 600 juga digunakan untuk terapi antidotum keracunan parasetamol yang disengaja maupun tidak.','Tiap tablet Effervescent mengandung Acetylcysteine 600 mg','ATURAN PAKAI HARUS SESUAI DENGAN PETUNJUK DOKTER. Treatment penyakit saluran pernafasan 1 tablet effervescent per hari (lebih baik pada malam hari), atau tergantung dari peresepan dokter. Untuk keracunan parasetamol yang disengaja maupun tidak, sesuai petunjuk dokter / apoteker.','Larutkan tablet effervescent ke dalam gelas yang berisi air secukupnya.','Obat Keras (Merah)','Dus, 5 Blister @ 2 Tablet Effervescent','Zambon','BPOM: DKI1214500411A1','https://d2qjkwm11akmwu.cloudfront.net/products/576839_6-4-2023_16-25-10.webp');
INSERT INTO obat VALUES(2,'Sterimar Blocked Nose Hypertonic 100 ml','Per Botol','Rp165.000 - Rp253.700','STERIMAR BLOCKED NOSE HYPERTONIC memiliki kandungan Sea water solution, 100% natural, digunakan untuk melegakan hidung tersumbat dan membersihkan area hidung.','Melegakan hidung tersumbat karena pilek atau sinusitis. Membersihkan area hidung sehingga dapat membantu pencegahan infeksi flu.','Sea water 75 ml dan Purified water 100 ml','2-6 semprotan di lubang hidung yang tersumbat','Sempotkan pada tiap lubang hidung. Lama penyemprotan 1-2 detik. Bersihkan nosel dengan air sabun han','Produk Konsumen','Dus, Botol @ 100 ml','Church & Dwight UK Limited','Kemenkes RI: AKL20704813984','https://d2qjkwm11akmwu.cloudfront.net/products/451159_20-12-2021_11-36-18-1665884910.webp');
INSERT INTO obat VALUES(3,'Woods Naturals Sirup 60 ml','Per Botol','Rp19.100 - Rp26.800','Woods Natural Sirup 60 ml merupakan herbal yang mengandung ekstrak daun Ivy, ekstrak daun meniran dan ekstrak daun mint. Herbal ini digunakan untuk membantu meredakan batuk berdahak.','Membantu meredakan batuk berdahak.','Tiap 5 ml sirup mengandung : Ekstrak daun ivy (hedera helix folii extract 35 mg, Ekstrak daun menira','Anak 2 - 5 tahun: 3 x sehari 2.5 ml, 6 - 12 tahun: 3 x sehari 5 ml, Anak > 12 tahun & Dewasa: 3 x sehari 10 ml','Berikan setelah makan, kocok dahulu sebelum diminum','Herbal','Dus, Botol @ 60 ml','Kalbe Farma','BPOM: TR132670781','https://d2qjkwm11akmwu.cloudfront.net/products/337368_1-4-2023_4-22-20.webp');
CREATE TABLE room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT INTO room_master_table VALUES(42,'6238f64ff35efc82f7e4eb5d1806cd86');
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('doctors',8);
INSERT INTO sqlite_sequence VALUES('jadwal',8);
INSERT INTO sqlite_sequence VALUES('user',1);
INSERT INTO sqlite_sequence VALUES('obat',3);
COMMIT;