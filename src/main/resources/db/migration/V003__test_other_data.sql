INSERT INTO coffee_machine_state
(id, factory_number, cleaning_required, tray_full, repair_required)
VALUES ('b7cb685c-29f0-4036-baa2-41f3df9c83d7', 'SuperPuperMachine3082', false, false, false);

INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('711c8f14-e0e5-4c54-b488-1763f3e37e38', '550e8400-e29b-41d4-a716-446655440000', true, 'можно создать;');
INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('2fbbe2d7-a185-435f-8769-fb9f7c4b7fe1', 'f9d2659f-a888-46af-8151-fc08186e759f', true, 'можно создать;');
INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('bea91ade-9045-45f8-ac05-c99034f0f1fc', '9d68fa23-42ac-43eb-814c-73c86fbd7ba9', true, 'можно создать;');
INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('adef7626-382f-4b5c-b727-14b7ed1ddd6e', '8304f2a6-7206-418d-a5f6-82e89e726e5a', true, 'можно создать;');
INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('d99d52c7-9868-401d-adf9-694f9ff32cd0', 'c4a6b3ee-87fb-4fb4-a07c-8df9d4ccfc51', true, 'можно создать;');
INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('0e8f4084-4832-47f5-ae27-e9a48f915b29', 'b1891667-7f3a-4c4c-ac43-ee4e4fcfc419', true, 'можно создать;');
INSERT INTO drink_status
(id, drink_id, can_create, text_status)
VALUES ('ea18240c-f44a-49eb-b25f-7b61dd8bc2b3', '400e176f-92a8-4362-b3b6-3c979c7a59a0', true, 'можно создать;');

INSERT INTO usage_counters
(id, coffmach_fact_numb_id, water_available_gr, milk_available_gr, beans_available_gr, drink_made_counter, liquid_in_tray_gr)
VALUES ('7a480d93-cb9d-49f5-bcae-81ee1f4421be', 'b7cb685c-29f0-4036-baa2-41f3df9c83d7', 1000, 500, 300, 0, 0);
