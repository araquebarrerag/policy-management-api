-- =========================
-- POLICIES
-- =========================

INSERT INTO policies (
    policy_number,
    type,
    start_date,
    end_date,
    monthly_rent_value,
    premium_value,
    status,
    renewal_enabled,
    created_at
)
VALUES
(
    1001,
    'COLLECTIVE',
    '2026-01-01',
    '2026-12-31',
    1500000.00,
    18000000.00,
    'ACTIVE',
    true,
    NOW()
),

(
    1002,
    'INDIVIDUAL',
    '2026-02-01',
    '2027-01-31',
    1200000.00,
    14400000.00,
    'ACTIVE',
    true,
    NOW()
),

(
    1003,
    'COLLECTIVE',
    '2026-03-01',
    '2027-02-28',
    2000000.00,
    24000000.00,
    'RENEWED',
    true,
    NOW()
),

(
    1004,
    'INDIVIDUAL',
    '2026-01-15',
    '2027-01-14',
    950000.00,
    11400000.00,
    'CANCELLED',
    false,
    NOW()
);

-- =========================
-- RISKS
-- =========================

INSERT INTO risks (
    tenant_name,
    landlord_name,
    property_address,
    insured_value,
    status,
    policy_id
)
VALUES

(
    'Juan Perez',
    'Gerardo Araque',
    'Cra 10 #20-30 Tunja',
    2000,
    'ACTIVE',
    1
),

(
    'Maria Gomez',
    'Diego Araque',
    'Calle 45 #18-90 Bogota',
    5000,
    'ACTIVE',
    1
),

(
    'Carlos Ramirez',
    'Diego Araque',
    'Av Boyaca #120-55 Bogota',
    6500,
    'ACTIVE',
    2
),

(
    'Laura Martinez',
    'Cesar Araque',
    'Cra 7 #100-20 Medellin',
    4700,
    'ACTIVE',
    3
),

(
    'Andres Lopez',
    'Jose Lopez',
    'Calle 80 #15-40 Cali',
    5700,
    'CANCELLED',
    4
);