-- Store 데이터 먼저 넣기
INSERT INTO store (id, name, detail_address, manager_number, location_id, region)
VALUES
(1, '반이학생이밥', '서울시 강남구', 01012345678, 1, 'SEOUL'),
(2, '맛있는 치킨', '서울시 서초구', 01087654321, 1, 'SEOUL'),
(3, '부산 회집', '부산시 해운대구', 01011112222, 2, 'BUSAN');

-- Mission 데이터 넣기
INSERT INTO mission (id, mission_spec, reward, mission_place, manager_id, store_id)
VALUES
(1, '10,000원 이상 구매 시', 500, '서울 강남', 1, 1),
(2, '리뷰 5개 작성하기', 1000, '서울 강남', 1, 1),
(3, '첫 방문 시 적립', 2000, '서울 서초', 1, 2),
(4, '5회 방문하면 보너스', 5000, '부산 해운대', 1, 3);