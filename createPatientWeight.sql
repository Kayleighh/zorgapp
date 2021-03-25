USE [ZorgApp]
GO

/****** Object:  Table [dbo].[PatientWeight]    Script Date: 25-3-2021 08:45:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PatientWeight](
	[weightId] [int] IDENTITY(1,1) NOT NULL,
	[weight] [decimal](18, 0) NOT NULL,
	[date] [datetime] NULL,
	[patId] [int] NOT NULL,
 CONSTRAINT [PK_Weight] PRIMARY KEY CLUSTERED 
(
	[weightId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PatientWeight]  WITH CHECK ADD  CONSTRAINT [FK_Weight_Patient] FOREIGN KEY([patId])
REFERENCES [dbo].[Patient] ([id])
GO

ALTER TABLE [dbo].[PatientWeight] CHECK CONSTRAINT [FK_Weight_Patient]
GO


