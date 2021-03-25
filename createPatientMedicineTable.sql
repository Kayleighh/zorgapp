USE [ZorgApp]
GO

/****** Object:  Table [dbo].[patientmedicine]    Script Date: 25-3-2021 08:45:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[patientmedicine](
	[patientId] [int] NOT NULL,
	[medicineId] [int] NOT NULL,
	[dosage] [nvarchar](50) NULL,
 CONSTRAINT [PK_patientmedicine] PRIMARY KEY CLUSTERED 
(
	[patientId] ASC,
	[medicineId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[patientmedicine]  WITH CHECK ADD  CONSTRAINT [FK_patientmedicine_Medicine] FOREIGN KEY([medicineId])
REFERENCES [dbo].[Medicine] ([id])
GO

ALTER TABLE [dbo].[patientmedicine] CHECK CONSTRAINT [FK_patientmedicine_Medicine]
GO

ALTER TABLE [dbo].[patientmedicine]  WITH CHECK ADD  CONSTRAINT [FK_patientmedicine_Patient] FOREIGN KEY([patientId])
REFERENCES [dbo].[Patient] ([id])
GO

ALTER TABLE [dbo].[patientmedicine] CHECK CONSTRAINT [FK_patientmedicine_Patient]
GO


